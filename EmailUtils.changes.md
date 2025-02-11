
# Changes to EmailUtils.createEmailObjectsMap

## Overview
The `createEmailObjectsMap` method in `EmailUtils.java` has been updated to handle a list of locales instead of a single locale. This change allows for more flexible email template generation that can support multiple languages in a single call.

## Changes Made

### 1. EmailUtils.java
- Updated method signature from `Map<String, String>` to `Map<String, Map<Locale, String>>`
- Changed parameter from `Locale locale` to `List<Locale> locales`
- Modified implementation to handle multiple locales

### 2. Dependent Files Updated
1. EmailTemplatesUtils.java
   - Updated all calls to createEmailObjectsMap
   - Added Collections import
   - Added HashMap import
   - Modified template token handling to extract locale-specific values

2. CustomerFacadeImpl.java
   - Updated call to createEmailObjectsMap
   - Added Collections import
   - Added HashMap import
   - Modified template token handling

3. UserFacadeImpl.java
   - Updated call to createEmailObjectsMap
   - Added Collections import
   - Added HashMap import
   - Modified template token handling

## Testing Impact
The following API endpoints need to be tested:
1. api/v1/cart/{}/checkout/
2. api/v1/auth/cart/{}/checkout/
3. api/v1/auth/orders/
4. api/v1/private/orders/customers/{}/
5. api/v1/private/orders/{}/

## Repository Dependencies
Based on the repository-wide dependencies analysis, no external repositories are directly impacted by this change as the email utility methods are internal to this repository.

## Testing Plan
1. Unit Tests:
   - Test createEmailObjectsMap with single locale
   - Test createEmailObjectsMap with multiple locales
   - Test createEmailObjectsMap with empty locale list
   - Verify template token structure
   - Verify locale-specific values are correctly stored

2. Integration Tests:
   - Test email generation with single locale
   - Test email generation with multiple locales
   - Verify email content is correct for each locale

3. API Tests:
   - Test affected API endpoints to ensure they work correctly with the updated email functionality
   - Verify email generation in different languages works as expected

## Migration Guide
For any code calling createEmailObjectsMap:
1. Update the method call to pass a List<Locale> instead of a single Locale
2. Update the code handling the returned Map to extract locale-specific values
3. Add necessary imports (Collections, HashMap)
4. Consider adding null checks for locale-specific values