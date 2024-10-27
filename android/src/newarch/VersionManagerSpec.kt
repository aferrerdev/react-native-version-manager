package com.versionmanager

import com.facebook.react.bridge.ReactApplicationContext

abstract class VersionManagerSpec internal constructor(context: ReactApplicationContext) :
  NativeVersionManagerSpec(context) {
}
