const URL = "http://localhost:8080/enrollments/all";

async function enrollment() {
    const request  = await fetch(URL);
    const response = await request.json();
    console.log(response);

    const table = document.querySelector("table");

    response.forEach((val) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${val.student ? val.student.name : "N/A"}</td>
         <td>${val.course ? val.course.name : "N/A"}</td> 
            <td>${val.id}</td>
            <td>${val.grade}</td>
            <td>${val.attendance}</td>
            <td><button class="btn1">Delete</button></td>
        `;

        const deletebtn = row.querySelector(".btn1");
        deletebtn.addEventListener("click", async () => {
            const confirmDelete = confirm(`Delete enrollment Id: ${val.id}?`);
            if (!confirmDelete) return;

            try {
                const delres = await fetch(`http://localhost:8080/enrollments/${val.id}`, {
                    method: "DELETE"
                });

                if (delres.ok) {
                    row.remove();
                    alert("Deleted");
                } else {
                    alert("Failed to delete, status: " + delres.status);
                }
            } catch (error) {
                console.error("Error:", error);
            }
        });

        table.appendChild(row);
    });
}

        const studentSelect  = document.querySelector(".student");
        const courseSelect  = document.querySelector(".course");
   async function loadStudent(){
           const request  = await fetch("http://localhost:8080/stuCon/allStudent");
           const respose = await request.json();
           respose.forEach(val=>{
                   const option  =  document.createElement("option");
                     option.value = val.id;
                     option.textContent = val.name;
                studentSelect.appendChild(option);
           }) ;
   }

   async function loadCourse(){
             const request  = await fetch("http://localhost:8080/course/courses");
             const respose = await request.json();
           respose.forEach(val=>{
                   const option  =  document.createElement("option");
                     option.value = val.id;
                     option.textContent = val.name;
                courseSelect.appendChild(option);
           }) ;
   }
  async function addStudInCour(event){
            event.preventDefault();
            const grade =document.querySelector(".Grade").value.trim();
            const attendance =document.querySelector(".Attendance").value.trim();
            const  studentId = studentSelect.value;
            const courseId = courseSelect.value;
             if(!grade || !attendance || !studentId || !courseId){
                alert("All field are requires!");
                return;
             }
            try{
                  const request  = await fetch("http://localhost:8080/enrollments/enroll",{
                     method:"POST",
                     headers:{
                          "Content-Type":"application/json"
                     },
                     body:JSON.stringify({
                        grade:grade,
                        attendance:attendance,
                        student:{id:studentId},
                        course:{id:courseId}
                     })
                  });
                      if(request.ok){
                        alert("sucessfully enroll student in course");
                        enrollment();
                      }
                      else{
                        alert("failed to enroll course :"+request.status);
                      }

            }
            catch(error){
                 console.log("error"+error);
            }
  }

window.onload  =()=>{ 
    enrollment();
    loadStudent();
    loadCourse();
}
  
