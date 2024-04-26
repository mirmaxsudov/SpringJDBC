const baseURL = "http://localhost:8080/";

function deleteToDo(id) {
    console.log(id);
    $('#deleteConfirmationModal').modal('show');

    $('#confirmDeleteBtn').on('click', function () {
        axios.delete(
            baseURL + id
        ).then((response) => {
            console.log("Success");
        }).catch((error) => {
            console.log(error);
        });
        $('#deleteConfirmationModal').modal('hide');
        location.reload();
    });
}

function makeDone(id) {
    console.log(id);

    axios.patch(
        baseURL + id
    ).then((response) => {
        console.log("Success");
    }).catch((error) => {
        console.log(error);
    })

    location.reload();
}

function addNewToDo() {
    const title = $('#title').val();
    const description = $('#description').val();

    const url = baseURL + "?title=" + title + "&description=" + description;

    axios.post(
        url
    ).then((response) => {
        console.log("Success");
    }).catch(error => {
        console.log(error);
    })

    location.reload();
}