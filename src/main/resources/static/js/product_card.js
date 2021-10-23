function productCard(product){

    const content = document.createElement("div")
    content.className = "product_card_content";
    content.append(addImg(product));
    content.append(title(product.title))
    content.append(shortDescription(product.shortDescription))

    const buttons = document.createElement("div")
    buttons.className = "product_card_buttons";
    buttons.append(move(product))
    buttons.append(addButton(product.id))

    const node = document.createElement("div");
    node.id = "product_card_"+ product.id;
    node.className = "product_card custom_panel";
    node.append(content);
    node.append(price(product.price))
    node.append(buttons);

    return node;
}

function addImg(ulr){
    const node = document.createElement("img");
    node.src = "https://static.eldorado.ru/photos/71/715/600/02/new_71560002_l_1624264756.jpeg/resize/380x240/";
    node.className = "card_img"
    return node;
}

function title(title){
    const node = document.createElement("p");
    node.textContent = title;
    node.className = "text_md product_card_heading"
    return node;
}

function price(price){
    const node = document.createElement("p");
    node.innerHTML = priceToString(price) +"&nbsp;&#8381;";
    node.className = "text_md text_price text_center"
    return node;
}

function shortDescription(shortDescription){
    const node = document.createElement("p");
    node.textContent = shortDescription;
    node.className = "text_sm text_dark text_optical product_card_info"
    return node;
}

function move(product){
    const node = document.createElement("a");
    node.textContent = "Подробно";
    node.className = "text_mini text_dark text_bold"
    return node;
}

function addButton(id){
    const div = document.createElement("div");
    div.id = "btn_add_" + id;
    const node = document.createElement("div");
    node.className = "btn_dark";
    node.textContent = "Добавить";
    node.addEventListener("click", ()=>{
        fetch("/cart/add/" + id).then(()=>{
            sendRequestCartTheShop();
        });
    })
    div.append(node);
    return div;
}
