let content = document.getElementById("content")
fetchSchools()

function fetchSchools(){
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

}
function generateContent(data){
	let text = "";
	for (let i = 0; i < data[0].teachers.length; i++) {
		// Add Rooms to Schools
		console.log(data[0].teachers[i]);
		text += `
				<div class=" box-flex-spaced-row school-teachers">
					<h2>${data[0].teachers[i].name}</h2>
	
					<div class="box-flex-centered-row values-teachers">
						<p>${data[0].teachers[i].subjects.length}</p>
						<p>0</p>
					</div>
				</div>
				`
	}
	return text;
}