function addRecommandation(hotel_id) {
    let addRecommandation = new XMLHttpRequest();
    addRecommandation.open("POST", "/addrecommandation", true);

    addRecommandation.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
    );
    addRecommandation.send("id=" + hotel_id);
}
