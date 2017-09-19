properties([
  parameters([
    choiceParam(
      name: 'ARCH',
      choices: "x86_64\nppc64le\naarch64",
      description: 'Architecture'
    ),
    string(
      name: 'ORIGIN_REPO',
      description: 'Origin repo',
      defaultValue: 'https://github.com/openshift/origin.git'
    ),
    string(
      name: 'ORIGIN_BRANCH',
      description: 'Origin branch',
      defaultValue: 'master'
    ),
    string(
      name: 'OS_BUILD_ENV_IMAGE',
      description: 'openshift-release image',
      defaultValue: 'openshiftmultiarch/origin-release:golang-1.8'
    )
  ])
])

node("paas-sig-ci-slave01") {
  ansiColor('xterm') {
    timestamps {
      echo 'hi'
    }
  }
}
