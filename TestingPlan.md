# Testing Plan for Deprecated Method Replacement

The following API endpoints need to be tested to ensure they function correctly after replacing the deprecated `getAdminFormatedAmount` method with `getStoreFormatedAmountWithCurrency`:

1. `api/v1/cart/{}/checkout/`
2. `api/v1/auth/cart/{}/checkout/`

These endpoints are located in the `OrderApi.java` file and should be tested for correct functionality and data integrity after the changes.
