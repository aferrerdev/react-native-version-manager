import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  getAppVersion(): Promise<string>;
  isAppVersionDeprecated(deprecatedVersions: Array<string>): Promise<boolean>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('VersionManager');
