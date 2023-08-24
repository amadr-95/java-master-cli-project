# CLI Car Booking Application

## Streams

We use streams where possible to retrieve data instead of using for loops.
For example:

**For each loop**
```
public List<Car> getAllAvailableCars() {
List<Car> availableCars = new ArrayList<>();
        for (Car car : getAllCars()) {
            if (car.isAvailable())
                availableCars.add(car);
        }
        return availableCars;
}
```
**Streams**
```
public List<Car> getAllAvailableCars() {
        return getAllCars().stream()
                .filter(Car::isAvailable)
                .toList();
    }
```
