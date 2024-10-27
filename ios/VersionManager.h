
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNVersionManagerSpec.h"

@interface VersionManager : NSObject <NativeVersionManagerSpec>
#else
#import <React/RCTBridgeModule.h>

@interface VersionManager : NSObject <RCTBridgeModule>
#endif

@end
