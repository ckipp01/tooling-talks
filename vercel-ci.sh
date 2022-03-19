#!/usr/bin/env sh

# Seemingly the easiest way to get everything to work in the CI that vercel has
# since the standalong launcher does't seem to pull down the jdk 17 that is
# actually needed. So for now, just rely on scala-cli locally and then use this
# in CI for vercel.
curl -fL https://github.com/Virtuslab/scala-cli/releases/latest/download/scala-cli-x86_64-pc-linux.gz | gzip -d > scala-cli
chmod +x scala-cli
./scala-cli run src/
