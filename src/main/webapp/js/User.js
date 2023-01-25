function deleteUser(id_utilisateur) {
    let deleteUser = new XMLHttpRequest();
    deleteUser.open("POST", "/p/reservation", true);

    deleteUser.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
    );
    deleteUser.send("id=" + id_utilisateur);
}