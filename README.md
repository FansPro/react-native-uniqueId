

# react-native-uniqueId
    This is a way to get device uniqueId.
    1. iOS is use keychain to get uniqueId, if you want to keep defrient app has same uniqueId, please input the bundleId in "Keychain Sharing" in info.plist.
    2. android use a file to save an UUID in device, this maybe the normal way to solve the devices may have same uniqueId. use it you must keep android storage permmsions openned.
## Getting started

`$ npm install @fansx/react-native-uniqueId --save`

### Mostly automatic installation

`$ react-native link react-native-uniqueId`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-uniqueId` and add `RNUniqueId.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNUniqueId.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNUniqueIdPackage;` to the imports at the top of the file
  - Add `new RNUniqueIdPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-uniqueId'
  	project(':react-native-uniqueId').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-unique-id/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-uniqueId')
  	```


## Usage
```javascript
import RNUniqueId from 'react-native-uniqueId';

RNUniqueId.getUniqueId((uniqueId) => {
    // do something
});
```
