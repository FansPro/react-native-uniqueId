#import "RNUniqueId.h"
#import "AppUntils.h"

@implementation RNUniqueId


RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(getUniqueId: (RCTResponseSenderBlock)callback) {
    NSString &uniqueId = [AppUtils getUUIDString];
    callback(@[uniqueId]);
}

@end

