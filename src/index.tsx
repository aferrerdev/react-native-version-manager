import {NativeModules, Platform} from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-version-manager' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ios: "- You have run 'pod install'\n", default: ''}) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const VersionManagerModule = isTurboModuleEnabled
  ? require('./NativeVersionManager').default
  : NativeModules.VersionManager;

const VersionManager = VersionManagerModule
  ? VersionManagerModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      },
    );

export const getAppVersion = () => {
  return VersionManager.getAppVersion();
};

export const isAppVersionDeprecated = (deprecatedVersions: Array<string>) => {
  return VersionManager.isAppVersionDeprecated(deprecatedVersions);
};
