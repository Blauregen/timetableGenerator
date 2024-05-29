let content = document.getElementById("content")
fetchSchools()
function fetchSchools(){
	fetch("http://localhost:8080/api/getAllSchools")
		.then((response) => response.json())
		.then((data) =>{
			console.log(data)
			let text = generateContent(data)
			content.innerHTML = text;
		})
		.catch((error)=>{
			console.log(error)
		})
}
function generateContent(data){
	let text = "";
	for (let i = 0; i < data.length; i++) {
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
							<p>${0}</p>
						</div>
					</div>
				</a>
				`
	}
	return text;
}