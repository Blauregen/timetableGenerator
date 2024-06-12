let content = document.getElementById("content")
let rooms;
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
	for (let i = 0; i < data.length; i++) {
		// Add Rooms to Schools
		data[i].rooms = rooms
		text += `
				<a class="box-flex-spaced-row school" href="./view.html">
					<h2>${data[i].name}</h2>
					<div class="box-flex-end-row values">
						<div class="box-flex-centered-row item">
							<img src="../img/teacher.png" alt="teacher">
							<p>${data[i].teachers.length}</p>
						</div>			
						<div class="box-flex-centered-row item">
							<img src="../img/rooms.png" alt="rooms">
							<p>${data[i].rooms.length}</p>
						</div>
					</div>
				</a>
				`
	}
	return text;
}