
## [Unreleased]
### Added
- Added a new boolean field `ship_ASAP` to the `ShippingSummary` class to support shipping products as soon as available in multiple shipments.
- Updated `ShippingQuoteServiceImpl` to set the `ship_ASAP` field when creating a `ShippingSummary` object.