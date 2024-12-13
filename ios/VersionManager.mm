#import "VersionManager.h"

@implementation VersionManager
RCT_EXPORT_MODULE(VersionManager)


RCT_EXPORT_METHOD(getAppVersion:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
  NSString *version = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"];
  if (version != nil) {
    resolve(version);
  } else {
    reject(@"ERROR", @"Failed to get app version", nil);
  }
}

RCT_EXPORT_METHOD(isAppVersionDeprecated:(NSArray<NSString *> *)deprecatedVersions
                                resolve:(RCTPromiseResolveBlock)resolve
                                reject:(RCTPromiseRejectBlock)reject)
{
  NSString *version = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"];
  if (version) {
    BOOL isDeprecated = [deprecatedVersions containsObject:version];
    // Resolve with a boolean indicating if the version is deprecated
    resolve(@(isDeprecated));  
  } else {
    reject(@"ERROR", @"Failed to get app version", nil);
  }
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeVersionManagerSpecJSI>(params);
}
#endif

@end
