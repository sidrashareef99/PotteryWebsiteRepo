import data from "./data.js";

function generateProductCards(productData) {
    const productGrid = document.getElementById("productGrid");

    productData.forEach((item) => {
        let card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
          <div class="image-container">
              <img src="${item.image}"  alt="">
          </div>
          <div class="details">
              <h3>${item.name} ${item.length} ${item.width} ${item.height}</h3>
          </div>
      `;

        productGrid.appendChild(card);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    generateProductCards(data);
});