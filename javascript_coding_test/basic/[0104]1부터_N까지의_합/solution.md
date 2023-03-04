# Solution

# point i should remember
```
const func = (n) => {
		/*pass by value*/
	let passVal = n; // n = 10;
	n++;

	console.log(n); // 9
	console.log(passVal); // 10


		/*pass by reference*/
	let object = {title: 'a'};
	let passObj = object;

	passObj.title = 'b';

	console.log(object.title); // b
}

```
