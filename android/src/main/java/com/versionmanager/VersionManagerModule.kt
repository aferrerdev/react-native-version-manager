package com.versionmanager

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import java.util.HashSet

class VersionManagerModule internal constructor(context: ReactApplicationContext) :
  VersionManagerSpec(context) {

  override fun getName(): String {
    return NAME;
  }

  @ReactMethod
  override fun getAppVersion(promise: Promise) {
      try {
          val context = reactApplicationContext
          val packageManager = context.packageManager
          val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
          val version = packageInfo.versionName
          promise.resolve(version)
      } catch (e: Exception) {
          promise.reject("ERROR", "Failed to get app version", e)
      }
  }

  @ReactMethod
  override fun isAppVersionDeprecated(deprecatedVersions: ReadableArray, promise: Promise) {
      try {
          val context = reactApplicationContext
          val packageManager = context.packageManager
          val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
          val currentVersion = packageInfo.versionName

          // Use HashSet for faster lookup
          val deprecatedSet = HashSet<String>()
          for (i in 0 until deprecatedVersions.size()) {
              deprecatedVersions.getString(i)?.let { version ->
                  deprecatedSet.add(version)
              }
          }

          // Check if the current version is in the deprecated set
          val isDeprecated = deprecatedSet.contains(currentVersion)
          promise.resolve(isDeprecated)
      } catch (e: Exception) {
          promise.reject("ERROR", "Failed to determine if app version is deprecated", e)
      }
  }

  companion object {
    const val NAME = "VersionManager"
  }
}
