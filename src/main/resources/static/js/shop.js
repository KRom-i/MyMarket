const cartTotalPrice = document.getElementById("cart-total-price");
const connectCartURL = "/shopping-cart";
const responseCartURL = "/cart/";
const requestCartURL = "/cart/add/";
const requestUserURL = "/shop/get-user";

function addProductToCart(id){
    fetch(requestCartURL + id);
}

sendRequestGet(requestUserURL)
    .then(data => (
        connect(data)))
    .catch(err =>(
        console.log(err)))

function sendRequestGet(url){
    return fetch(url).then(response => {
        return response.json()
    })
}

function connect(user) {
    const socket = new SockJS(connectCartURL);
    const client = Stomp.over(socket);
    updateTotalPrice(user.cart)
    client.connect({}, (frame) => {
        client.subscribe(responseCartURL + user.id,
            (response) => {
                updateTotalPrice(JSON.parse(response.body));
        });
    });

}

function updateTotalPrice(cart) {
    if (!cart.totalPrice){
        cartTotalPrice.textContent = priceToString(0);
        return;
    }
    cartTotalPrice.textContent = priceToString(cart.totalPrice);
}

function priceToString(price){
    return parseFloat(price).toFixed(2)
}