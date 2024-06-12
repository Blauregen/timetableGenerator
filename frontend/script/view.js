content = document.getElementById("school-view")
fetchSchools()
function fetchSchools(){
	// Fetching Rooms
	fetch("http://localhost:8080/api/getAllRooms")
		.then((response) => response.json())
		.then((data) =>{
			console.log(data)
			rooms = data
			// Fetch Schools
			fetch("http://localhost:8080/api/getAllSchools")
				.then((response) => response.json())
				.then((data) =>{
					console.log(data)
					content.innerHTML = generateContent(data);
				})
				.catch((error)=>{
					console.log(error)
				})
		})
		.catch((error)=>{
			console.log(error)
		})

}
function generateContent(data){
	let text = "";
	// Add Rooms to Schools
	data[0].rooms = rooms
	text += `
			
	<a id="back" class="box-flex-centered-row" href="./index.html">
		<img src="../img/arrow.svg">
	</a>

	<div class="box-flex-centered-column">
		<h2>${data[0].name}</h2>

		<div id="view-values" class="box-flex-centered-row">
			<a class="box-flex-centered-column info" href="./teachers.html">
				<p>Teachers</p>
				<p>${data[0].teachers.length}</p>
			</a>
			<div class="box-flex-centered-column info">
				<p>Rooms</p>
				<p>${data[0].rooms.length}</p>
			</div>
			<div class="box-flex-centered-column info">
				<p>Subjects</p>
				<p>0</p>
			</div>
			<div class="box-flex-centered-column info">
				<p>Classes</p>
				<p>${data[0].schoolClasses.length}</p>
			</div>
		</div>
	</div>
			`
	return text;
}