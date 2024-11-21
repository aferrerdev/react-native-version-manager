package com.versionmanager

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReadableArray

abstract class VersionManagerSpec internal constructor(context: ReactApplicationContext) :
  ReactContextBaseJavaModule(context) {

  abstract fun getAppVersion(promise: Promise)

  abstract fun isAppVersionDeprecated(deprecatedVersions: ReadableArray, promise: Promise)
}
