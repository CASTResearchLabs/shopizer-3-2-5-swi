# Problem Analysis and Proposed Solution

## Problem Analysis
The `ProductPriceUtils.getAdminFormatedAmount` method is deprecated due to an unused parameter and needs to be replaced with `getStoreFormatedAmountWithCurrency`. This change affects several files that call the deprecated method, as well as API endpoints that rely on it.

### Affected Files
- `PricingServiceImpl.java`
- `StripePayment.java`
- `Stripe3Payment.java`
- `BeanStreamPayment.java`
- `StorePickupShippingQuote.java`
- `USPSShippingQuote.java`

### Affected API Endpoints
- `api/v1/cart/{}/checkout/`
- `api/v1/auth/cart/{}/checkout/`

## Proposed Solution
1. **Replace Method Calls**: Replace all calls to `getAdminFormatedAmount` with `getStoreFormatedAmountWithCurrency` in the affected files.
2. **Make Method Static**: Convert `getAdminFormatedAmount` to a static method to prevent future usage.
3. **Testing**: Test the affected API endpoints to ensure they function correctly after the changes.

## Impact Analysis
- **Direct Callers**: The direct callers of the deprecated method will be updated to use the new method, ensuring compatibility.
- **API Endpoints**: The identified API endpoints will be tested to verify that they continue to operate as expected.

This solution ensures that the deprecated method is no longer used, while maintaining the functionality of the system.

