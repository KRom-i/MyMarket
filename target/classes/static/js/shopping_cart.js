const cartTotalPrice = document.getElementById("cart-total-price");
const cartOrder = document.getElementById("cart-order");

// const connectCartURL = "/shopping-cart";
// const responseCartURL = "/cart/";
// const requestCartURL = "/cart/add/";
// const requestUserURL = "/shop/get-user";
// function connectShop(){
//     sendRequestGet(requestUserURL)
//         .then(data => (
//             connectShopCart(data)))
//         .catch(err =>(
//             console.log(err)))
// }
//
// function connectShopCart(user) {
//     const socket = new SockJS(connectCartURL);
//     const client = Stomp.over(socket);
//     updateShopCart(user.cart)
//     client.connect({}, (frame) => {
//         client.subscribe(responseCartURL + user.id,
//             (response) => {
//                 updateShopCart(JSON.parse(response.body));
//         });
//     });
// }

function btnToShoppingCart(){
    const a = document.createElement("a");
    a.textContent = "В корзине";
    a.href = "/cart";
    a.className = "btn_light";
    a.style = "text-decoration: none;"
    return a;
}

function sendRequestCartTheShop(){
    sendRequestGet("/cart/get")
        .then(data => (updateShoppingCartTheShop(data)))
        .catch(err =>(console.log(err)))
}

function updateShoppingCartTheShop(cart){
    if (cart.orderItems){
        for (let i = 0; i < cart.orderItems.length; i++) {
            let id = cart.orderItems[i].product.id
            let product_card = document.getElementById("product_card_" + id)
            if (product_card){
                product_card.className = product_card.className + " " + "card_border";
            }
            let div = document.getElementById("btn_add_" + id)
            if (div){
                div.textContent = "";
                div.append(btnToShoppingCart())
            }
        }
    }
   updateValuesTheShop(cart)
}

function updateValuesTheShop(cart){
    if (!cart.totalPrice){
        cartTotalPrice.textContent = priceToString(0);
        cartOrder.textContent = "0";
        return;
    }
    cartTotalPrice.textContent = priceToString(cart.totalPrice);
    cartOrder.textContent = cart.quantity;
}

function priceToString(price){
    return parseFloat(price).toFixed(2).
    replace(/(\d{1,3}(?=(?:\d\d\d)+(?!\d)))/g, "$1" + ' ');
}

function priceToStringAndRub(price){
    return parseFloat(price).toFixed(2).
    replace(/(\d{1,3}(?=(?:\d\d\d)+(?!\d)))/g, "$1" + ' ') + "&nbsp;&#8381;";
}