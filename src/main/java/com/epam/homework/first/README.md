## Домашняя работа №1
### Постановка задачи
Написать свою реализацию списка( List) со следующим набором методов:

- boolean add(T element) - добавить элемент. Возвращает true – если элемент добавлен

- T remove(int index) - удалить элемент по индексу. Возвращает удаленный элемент

- int remove(T element) - удалить первое вхождение элемента. Возвращает индекс удаленного элемента

- void copy(YourList<? super T> to, YourList<? extends T> from) – копировать коллекцию from в коллекцию to

YourList -  ваша реализациия коллекции.

### Решение задачи
- В файле MyList.java содержится реализация списка.
- В файле DemoMyList.java содержатся примеры взаимодействия с реализованным списком.
