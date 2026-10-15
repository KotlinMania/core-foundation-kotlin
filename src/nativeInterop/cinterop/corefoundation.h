#ifndef COREFOUNDATION_CINTEROP_H
#define COREFOUNDATION_CINTEROP_H

#include <stddef.h>
#include <stdint.h>
#include <stdbool.h>

typedef const void* CFTypeRef;
typedef uintptr_t CFTypeID;
typedef uintptr_t CFOptionFlags;
typedef uintptr_t CFHashCode;
typedef intptr_t CFIndex;
typedef uint32_t CFStringEncoding;
typedef bool Boolean;
typedef uint8_t UInt8;

typedef struct CFRange {
    CFIndex location;
    CFIndex length;
} CFRange;

static inline CFRange CFRangeMake(CFIndex loc, CFIndex len) {
    CFRange r;
    r.location = loc;
    r.length = len;
    return r;
}

typedef struct __CFString* CFMutableStringRef;
typedef const struct __CFString* CFStringRef;
typedef const void* CFAllocatorRef;

#define kCFAllocatorDefault NULL
#define kCFAllocatorNull NULL

#define kCFStringEncodingMacRoman       ((CFStringEncoding)0)
#define kCFStringEncodingWindowsLatin1  ((CFStringEncoding)0x0500)
#define kCFStringEncodingISOLatin1      ((CFStringEncoding)0x0201)
#define kCFStringEncodingNextStepLatin  ((CFStringEncoding)0x0B01)
#define kCFStringEncodingASCII          ((CFStringEncoding)0x0600)
#define kCFStringEncodingUnicode        ((CFStringEncoding)0x0100)
#define kCFStringEncodingUTF8           ((CFStringEncoding)0x08000100)
#define kCFStringEncodingNonLossyASCII  ((CFStringEncoding)0x0BFF)
#define kCFStringEncodingUTF16          ((CFStringEncoding)0x0100)
#define kCFStringEncodingUTF16BE        ((CFStringEncoding)0x10000100)
#define kCFStringEncodingUTF16LE        ((CFStringEncoding)0x14000100)
#define kCFStringEncodingUTF32          ((CFStringEncoding)0x0c000100)
#define kCFStringEncodingUTF32BE        ((CFStringEncoding)0x18000100)
#define kCFStringEncodingUTF32LE        ((CFStringEncoding)0x1c000100)

CFTypeRef CFRetain(CFTypeRef cf);
void CFRelease(CFTypeRef cf);
CFTypeID CFGetTypeID(CFTypeRef cf);
CFIndex CFGetRetainCount(CFTypeRef cf);
Boolean CFEqual(CFTypeRef cf1, CFTypeRef cf2);
CFHashCode CFHash(CFTypeRef cf);
void CFShow(CFTypeRef obj);

CFStringRef CFStringCreateWithBytes(
    CFAllocatorRef alloc,
    const UInt8 *bytes,
    CFIndex numBytes,
    CFStringEncoding encoding,
    Boolean isExternalRepresentation
);
CFIndex CFStringGetLength(CFStringRef theString);
const char* CFStringGetCStringPtr(CFStringRef theString, CFStringEncoding encoding);
CFIndex CFStringGetBytes(
    CFStringRef theString,
    CFRange range,
    CFStringEncoding encoding,
    UInt8 lossByte,
    Boolean isExternalRepresentation,
    UInt8 *buffer,
    CFIndex maxBufLen,
    CFIndex *usedBufLen
);
CFTypeID CFStringGetTypeID(void);

#endif /* COREFOUNDATION_CINTEROP_H */
