# Release Notes

## v1.1.0 (порівняно з v1.0.0)

### Added
- Спеціальний тариф доставки для міста Київ: базова ставка 80.0.
- Явний параметр ставки податку `taxRate` у `processStandardInvoice(...)`. Перевантажений метод без цього параметра використовує значення за замовчуванням 0.20.
- Окремий метод `processTaxExemptInvoice(...)` для розрахунку без податку.

### Changed
- Коефіцієнт вартості доставки за вагу збільшено з 12.0 до 15.0 за кг.
- Виконано перехід до Data Coupling: методи приймають лише примітивні дані (`city`, `weightKg`, `amount`, `taxRate`), а не весь об'єкт `CustomerProfile`.
- Логіку розрахунку рахунку розділено на два окремі методи замість одного з керувальним прапорцем.

### Breaking Changes
- Змінено сигнатуру `calculateDeliveryCost`: замість `(CustomerProfile customer, double weightKg)` тепер `(String city, double weightKg)`.
- Метод `processInvoice(amount, customer, weightKg, modeFlag)` видалено. Замість нього використовуйте `processStandardInvoice(...)` та `processTaxExemptInvoice(...)`.
- Видалено глобальну змінну `GLOBAL_TAX_RATE`. Ставку податку тепер передають явно через `taxRate`.
- Видалено прапорець `modeFlag`, разом із ним зникла гілка зі статусом `CANCELLED`.
