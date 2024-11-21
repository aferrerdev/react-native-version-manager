package com.versionmanager

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray

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

          // Convert ReadableArray to List<String>
          val deprecatedList = mutableListOf<String>()
          for (i in 0 until deprecatedVersions.size()) {
              val version = deprecatedVersions.getString(i)
              if (version != null) {
                  deprecatedList.add(version)
              }
          }

          // Check if the current version is in the deprecated list
          val isDeprecated = deprecatedList.contains(currentVersion)
          promise.resolve(isDeprecated)
      } catch (e: Exception) {
          promise.reject("ERROR", "Failed to determine if app version is deprecated", e)
      }
  }

  companion object {
    const val NAME = "VersionManager"
  }
}
