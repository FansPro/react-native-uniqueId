#import "RNUniqueId.h"
#import "AppUtils.h"

@implementation RNUniqueId


RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(getUniqueId: (RCTResponseSenderBlock)callback) {
    NSString *uniqueId = [AppUtils readUUIDFromKeyChain];
    callback(@[uniqueId]);
}

@end

