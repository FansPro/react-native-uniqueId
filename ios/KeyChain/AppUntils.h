//
//  AppUntils.h
//  Hello
//
//  Created by 彭帆 on 2018/2/1.
//  Copyright © 2018年 AXtech. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AppUntils : NSObject
+(void)saveUUIDToKeyChain;
+(NSString *)readUUIDFromKeyChain;
+ (NSString *)getUUIDString;
@end
