// src/app/product/page.tsx

'use client';

import { useState, useEffect } from 'react';
import Image from 'next/image';
import { FaEye } from 'react-icons/fa';
import { useCart } from '../context/CartContext';
import { getItems, getProductImageUrl, type Item } from '@/api/inventory';

// Helper function to get image URL or fallback
const getProductImage = (product: Item): string => {
    return getProductImageUrl(product);
};

export default function ProductPage() {
    const [products, setProducts] = useState<Item[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [selectedProduct, setSelectedProduct] = useState<Item | null>(null);
    const [isHovered, setIsHovered] = useState<string | null>(null);
    const [quantity, setQuantity] = useState(1);
    const { addToCart } = useCart();

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                setLoading(true);
                const data = await getItems();
                setProducts(data);
                setError(null);
            } catch (err) {
                setError('Failed to load products. Please try again later.');
                console.error('Error fetching products:', err);
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);

    const handleAddToCart = () => {
        if (selectedProduct) {
            // Convert Item to the format expected by cart
            const cartProduct = {
                id: selectedProduct.productId,
                name: selectedProduct.name,
                imageSrc: getProductImage(selectedProduct),
                price: selectedProduct.price,
                info: `Product ID: ${selectedProduct.productId}`,
                sku: selectedProduct.sku || `SKU-${selectedProduct.productId}`,
                quantity: selectedProduct.quantity
            };
            addToCart(cartProduct, quantity);
            setSelectedProduct(null);
            setQuantity(1);
        }
    };

    if (loading) {
        return (
            <div className="bg-white min-h-screen text-gray-800 flex items-center justify-center">
                <div className="text-center">
                    <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-blue-800 mx-auto mb-4"></div>
                    <p className="text-xl">Loading products...</p>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="bg-white min-h-screen text-gray-800 flex items-center justify-center">
                <div className="text-center">
                    <p className="text-xl text-red-600 mb-4">{error}</p>
                    <button 
                        onClick={() => window.location.reload()} 
                        className="bg-blue-800 hover:bg-blue-700 text-white px-6 py-3 rounded"
                    >
                        Try Again
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div className="bg-white min-h-screen text-gray-800">
            {/* Main content area */}
            <main className="py-20">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
                    <h1 className="text-5xl md:text-6xl font-extrabold mb-2 uppercase text-blue-800 tracking-wide leading-none">
                        OUR PRODUCTS
                    </h1>
                    <div className="w-16 h-1 bg-blue-800 mx-auto mb-12"></div>
                </div>

                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-8">
                    {products.length === 0 ? (
                        <div className="col-span-full text-center py-12">
                            <p className="text-xl text-gray-500">No products available at the moment.</p>
                            <p className="text-gray-400 mt-2">Please check back later or contact us for more information.</p>
                        </div>
                    ) : (
                        products.map(product => (
                            <div
                                key={product.productId}
                                className="relative text-center group"
                                onMouseEnter={() => setIsHovered(product.productId)}
                                onMouseLeave={() => setIsHovered(null)}
                            >
                                <div className="relative mb-4">
                                    <Image
                                        src={getProductImage(product)}
                                        alt={product.name}
                                        width={300}
                                        height={300}
                                        style={{ objectFit: 'contain' }}
                                        className="rounded-lg transition-transform duration-300 group-hover:scale-105"
                                        onError={(e) => {
                                            // Fallback to default image if product image fails to load
                                            const target = e.target as HTMLImageElement;
                                            target.src = '/images/main.png';
                                        }}
                                    />
                                    {product.quantity < 5 && product.quantity > 0 && (
                                        <span className="absolute top-2 left-2 bg-orange-500 text-white text-xs font-semibold px-2 py-1 rounded">
                                            Low Stock
                                        </span>
                                    )}
                                    {product.quantity === 0 && (
                                        <span className="absolute top-2 left-2 bg-red-500 text-white text-xs font-semibold px-2 py-1 rounded">
                                            Out of Stock
                                        </span>
                                    )}
                                    {isHovered === product.productId && (
                                        <div className="absolute bottom-0 left-0 right-0 py-2 bg-black bg-opacity-70 text-white transition-opacity duration-300">
                                            <button
                                                onClick={() => {
                                                    setSelectedProduct(product);
                                                    setQuantity(1);
                                                }}
                                                className="flex items-center justify-center mx-auto space-x-2 text-sm font-semibold hover:text-blue-400"
                                            >
                                                <FaEye />
                                                <span>VIEW</span>
                                            </button>
                                        </div>
                                    )}
                                </div>
                                <h3 className="font-semibold mb-1 line-clamp-2">{product.name}</h3>
                                <p className="text-black font-semibold">R{product.price.toFixed(2)}</p>
                                {product.quantity === 0 && (
                                    <p className="text-red-500 text-sm mt-1">Out of Stock</p>
                                )}
                            </div>
                        ))
                    )}
                </div>
            </main>

            {/* Product info modal */}
            {selectedProduct && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
                    <div className="bg-white p-8 rounded-lg shadow-2xl relative max-w-3xl w-full flex flex-col md:flex-row">
                        <button onClick={() => setSelectedProduct(null)} className="absolute top-4 right-4 text-gray-600 hover:text-gray-900 text-2xl font-bold">
                            &times;
                        </button>

                        {/* Left: Product Image */}
                        <div className="md:w-1/2 flex items-center justify-center mb-6 md:mb-0">
                            <Image
                                src={getProductImage(selectedProduct)}
                                alt={selectedProduct.name}
                                width={400}
                                height={400}
                                style={{ objectFit: 'contain' }}
                                className="rounded-lg"
                                onError={(e) => {
                                    // Fallback to default image if product image fails to load
                                    const target = e.target as HTMLImageElement;
                                    target.src = '/images/main.png';
                                }}
                            />
                        </div>

                        {/* Right: Product Details */}
                        <div className="md:w-1/2 md:pl-8">
                            <h2 className="text-3xl font-bold mb-2">{selectedProduct.name}</h2>
                            <p className="text-lg text-gray-600 mb-4">SKU: {selectedProduct.sku || selectedProduct.productId}</p>
                            <p className="text-2xl font-semibold text-blue-800 mb-4">R{selectedProduct.price.toFixed(2)}</p>

                            <div className="mb-4">
                                <p className="text-gray-600 mb-2">Product ID: {selectedProduct.productId}</p>
                                {selectedProduct.categoryId && (
                                    <p className="text-gray-600 mb-2">Category: {selectedProduct.categoryId}</p>
                                )}
                                {selectedProduct.supplierId && (
                                    <p className="text-gray-600 mb-2">Supplier: {selectedProduct.supplierId}</p>
                                )}
                            </div>

                            {selectedProduct.quantity > 0 ? (
                                <>
                                    <div className="flex items-center mb-6 space-x-4">
                                        <label htmlFor="quantity" className="text-lg font-semibold">Quantity:</label>
                                        <input
                                            type="number"
                                            id="quantity"
                                            value={quantity}
                                            min="1"
                                            max={selectedProduct.quantity}
                                            onChange={(e) => setQuantity(Math.min(Number(e.target.value), selectedProduct.quantity))}
                                            className="w-20 p-2 border border-gray-400 rounded-md text-center text-gray-900 bg-white"
                                        />
                                        <span className="text-gray-500 text-sm">({selectedProduct.quantity} in stock)</span>
                                    </div>

                                    <button
                                        onClick={handleAddToCart}
                                        className="w-full bg-blue-800 hover:bg-blue-700 text-white font-semibold py-3 px-8 text-lg rounded shadow-lg transition duration-300 ease-in-out"
                                    >
                                        ADD TO CART
                                    </button>
                                </>
                            ) : (
                                <div className="text-center">
                                    <p className="text-red-500 text-lg font-semibold mb-4">Out of Stock</p>
                                    <button
                                        disabled
                                        className="w-full bg-gray-400 text-white font-semibold py-3 px-8 text-lg rounded shadow-lg cursor-not-allowed"
                                    >
                                        UNAVAILABLE
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}