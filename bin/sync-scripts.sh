
GERMANIUM_JAVA=$(readlink -f $(dirname $(readlink -f "$0"))/../)
GERMANIUM_PYTHON=$(readlink -f $(dirname $(readlink -f "$0"))/../../germanium/)

cp -R $GERMANIUM_PYTHON/germanium/locators/*.js \
      $GERMANIUM_JAVA/src/main/resources/germanium/locators/

