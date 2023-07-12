# amazing-numbers

- based on number thoery

- enter natural number for its properties

- you can specify how many numbers you want starting from that number ```65 4```

- you can also add properties you want ```64 4 duck```

-  "-" are properties which are avoided in numbers ```64 4 -odd```

-   add any number of availaible properties you want (or don't)

-  enter 'properties' to see availaible properties
  <br></br>
```
Welcome to Amazing Numbers!

Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
* the first parameter represents a starting number;
* the second parameter shows how many consecutive numbers are to be processed;
- two natural numbers and properties to search for;
- a property preceded by minus must not be present in numbers;
- separate the parameters with one space;
- enter 'properties' to see available properties;
- enter 0 to exit.

Enter a request:
12

Properties of 12
		buzz: false
		duck: false
		palindromic: false
		gapful: false
		spy: false
		square: false
		sunny: false
		jumping: true
		happy: false
		sad: true
		even: true
		odd: false

Enter a request:
65 4

		65 is [jumping, sad, odd]
		66 is [palindromic, sad, even]
		67 is [buzz, jumping, sad, odd]
		68 is [happy, even]

Enter a request:
32 2 even

		32 is [jumping, happy, even]
		34 is [jumping, sad, even]

Enter a request:
32 2 -even square

		49 is [buzz, square, happy, odd]
		81 is [square, sad, odd]

Enter a request:
67 0
The second parameter should be a natural number.


Enter a request:
69 8 you red
The properties [YOU, RED] are wrong.
Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING, HAPPY, SAD]

Enter a request:
0
Goodbye!

Process finished with exit code 0
```

##### <i>hyperskill 'java developer' track project</i>
