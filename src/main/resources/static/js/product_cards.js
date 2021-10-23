const productCards = document.getElementById("product_cards");

filterAddListeners();
updateProductCards(page);

function updateProductCards(page){
    console.log(page);
    productCards.textContent = "";
    document.getElementById("order_product").textContent = page.totalElements;
    const products = page.content;
    for (let i = 0; i < products.length; i++) {
        const card = productCard(products[i]);
        productCards.append(card);
    }

    sendRequestCartTheShop();
    updatePagerBottom(page);
}

function sendRequestPage(){
    sendRequestGet("/shop/page" + params())
        .then(data => (updateProductCards(data)))
        .catch(err =>(console.log(err)))
}












