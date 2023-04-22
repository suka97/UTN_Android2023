package com.suka.superahorro.entities

class ShopItemsRepository {
    var shopItems = mutableListOf<ShopItem>()

    init {
        shopItems.add(ShopItem(1, "Chocolate de Taza", 500, 1, "https://images.hola.com/imagenes/cocina/recetas/20191120154549/chocolate-taza-tradicional/0-748-823/chocolate-taza-t.jpg?tx=w_1200"))
        shopItems.add(ShopItem(2, "Leche", 200, 2))
        shopItems.add(ShopItem(3, "Tapa de Tarta", 300, 1, "https://dulcilandia.com.ar/sfe/wp-content/uploads/sites/3/2020/05/02740672-510x510.png"))
        shopItems.add(ShopItem(4, "Aceite de Oliva", 1200, 1))
    }
}