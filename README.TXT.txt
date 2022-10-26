http://localhost:8080/go - чтение значений с файла, если указан тип запрашиваемой операции, функция вернет json объект с расположением файла.
http://localhost:8080/get_max_value - получить расположение файла с результатом операции нахождения максимального значения. Путь файла - формат json, файл - json/xml зависит от header.
http://localhost:8080/get_min_value - получить расположение файла с результатом операции нахождения минимального значения. Путь файла - формат json, файл - json/xml зависит от header.
http://localhost:8080/get_median - получить расположение файла с результатом операции нахождения медианы. Путь файла - формат json, файл - json/xml зависит от header.
http://localhost:8080/get_average - получить расположение файла с результатом операции нахождения среднего значения. Путь файла - формат json, файл - json/xml зависит от header.
http://localhost:8080/get_increasing_numbers - получить расположение файла с результатом операции нахождения самой длинной последовательности идущих подряд чисел, которая увеличивается. Путь файла - формат json, файл - json/xml зависит от header.
http://localhost:8080/get_decreasing_numbers - получить расположение файла с результатом операции нахождения самой длинной последовательности идущих подряд чисел, которая уменьшается. Путь файла - формат json, файл - json/xml зависит от header.




http://localhost:8080/get_median  header - xml

<?xml version=1.0 encoding=UTF-8?>
<root>
<median>25216.0</median>
</root>


http://localhost:8080/get_decreasing_numbers header - json

{
  "decreasing_numbers": [
    [
      47689379,
      42381213,
      30043880,
      12102356,
      -4774057,
      -5157723,
      -11217378,
      -23005285,
      -23016933,
      -39209115,
      -49148762
    ]
  ]
}


http://localhost:8080/get_max_value header - json

{
  "max_value": 49999978
}


http://localhost:8080/get_increasing_numbers  header - xml

<?xml version=1.0 encoding=UTF-8?>
<root>
<increasing_numbers>
<elements>
<element>-48190694<element/>
<element>-47725447<element/>
<element>-43038241<element/>
<element>-20190291<element/>
<element>-17190728<element/>
<element>-6172572<element/>
<element>8475960<element/>
<element>25205909<element/>
<element>48332507<element/>
<element>48676185<element/>
<elements/>
<increasing_numbers/>
<root/>


