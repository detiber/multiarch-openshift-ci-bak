def call(List<String> dirs=[]) {
  try {
    unstash('test.hostname')
    def test_hostname = readFile('test.hostname').replaceAll("\\s","")
    echo "test_hostname: ${test_hostname}"
    for (d in dirs) {
      withEnv(["test_hostname=${test_hostname}", "d=${d}"]) {
        sh('''#!/usr/bin/bash -xeu
              ssh_opts="-o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -l root"
	      rsync -azhe "ssh ${ssh_opts}" "${d}" "${test_hostname}:"
           ''')
      }
    }
  }
  catch (err) {
    echo err.getMessage()
    throw err
  }
}
