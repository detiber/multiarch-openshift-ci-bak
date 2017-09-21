def call(Closure body) {
  try {
    sh('''#!/usr/bin/bash -xeu
          if [[ "${ARCH}" == "x86_64" ]]; then
            ssid=$(cico node get -f value -c comment)
          else
            ssid=$(cico node get -f value -c comment --arch "${ARCH}" --flavor xram.medium )
          fi
          if [[ -z "${ssid:-}" ]]; then
            echo "Failed to provision duffy host"
            exit 1
          fi

          cico inventory --ssid ${ssid}

          echo "${ssid}" > duffy.ssid
	  cico inventory --ssid ${ssid} -f value -c hostname > duffy.hostname
          cico inventory --ssid ${ssid} -f json > duffy.inventory
       ''')
    archiveArtifacts 'duffy.ssid'
    archiveArtifacts 'duffy.hostname'
    archiveArtifacts 'duffy.inventory'
    body()
  }
  catch (err) {
    echo err.getMessage()
    throw err
  }
  finally {
     sh('''#!/usr/bin/bash -xeu
          ssid=$(cat duffy.ssid)
          if [[ -n "${ssid:-}" ]]; then
            cico node done $ssid
          fi
       ''')
  }
}
