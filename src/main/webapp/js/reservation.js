function addReservation(hotel_id) {
    let addReservation = new XMLHttpRequest();
    addReservation.open("POST", "/addreservation", true);

    addReservation.setRequestHeader(
      "Content-type",
      "application/x-www-form-urlencoded"
    );
    addReservation.send("id=" + hotel_id);
}

function deleteReservation(hotel_id) {
    let deleteReservation = new XMLHttpRequest();
    deleteReservation.open("POST", "/p/reservation", true);

    deleteReservation.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
    );
    deleteReservation.send("id=" + hotel_id);
}
