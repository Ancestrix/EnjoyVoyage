function deleteHotel(hotel_id) {
    let deleteHotel = new XMLHttpRequest();
    deleteHotel.open("POST", "/p/admin/deletehotel", true);

    deleteHotel.setRequestHeader(
        "Content-type",
        "application/x-www-form-urlencoded"
    );
    deleteHotel.send("id=" + hotel_id);
}