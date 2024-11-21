# React Native Version Manager

A lightweight React Native module for managing app versions. This module provides two key functionalities:

1. **Retrieve the current app version.**
2. **Check if the current app version is deprecated.**

## Installation

```bash
npm install react-native-version-manager
```

or

```bash
yarn add react-native-version-manager
```

### Linking

If you're using React Native 0.60 or higher, the module is automatically linked. For older versions, link it manually:

```bash
react-native link react-native-version-manager
```

## Usage

### Importing the Module

```javascript
import {
  getAppVersion,
  isAppVersionDeprecated,
} from 'react-native-version-manager';
```

### Methods

#### `getAppVersion(): Promise<string>`

Fetches the current app version from the native side.

##### Example:

```javascript
getAppVersion()
  .then(version => {
    console.log('App Version:', version);
  })
  .catch(error => {
    console.error('Failed to get app version:', error);
  });
```

#### `isAppVersionDeprecated(deprecatedVersions: Array<string>): Promise<boolean>`

Checks if the current app version is in a list of deprecated versions.

##### Parameters:

- `deprecatedVersions`: An array of strings representing deprecated app versions.

##### Example:

```javascript
const deprecatedVersions = ['1.0', '1.1'];

isAppVersionDeprecated(deprecatedVersions)
  .then(isDeprecated => {
    if (isDeprecated) {
      console.log('The current app version is deprecated.');
    } else {
      console.log('The current app version is up-to-date.');
    }
  })
  .catch(error => {
    console.error('Error checking app version:', error);
  });
```

## Native Integration

### iOS

Ensure the module is properly linked and the following code is included in your iOS native implementation to fetch the app version from the `Info.plist`.

### Android

The module uses the `PackageManager` to retrieve the app version.

## Troubleshooting

- **TypeError: Undefined methods**  
  Ensure the module is correctly linked and you’ve rebuilt your app (`npx react-native run-android` or `npx react-native run-ios`).

- **Version Not Found**  
  Double-check that your app’s version information is set in the appropriate location:
  - iOS: `CFBundleShortVersionString` in `Info.plist`.
  - Android: `versionName` in `build.gradle`.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License.
