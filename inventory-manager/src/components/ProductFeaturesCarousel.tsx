'use client';

import { useState } from 'react';
import {
  FaChevronLeft,
  FaChevronRight,
  FaShoppingCart,
  FaTruck,
  FaShieldAlt,
  FaUsers,
  FaHeadset,
  FaStar,
} from 'react-icons/fa';

const featureSlides = [
  {
    icon: <FaShoppingCart className="text-5xl text-teal-400" />,
    title: 'Easy Shopping Experience',
    description:
      'Browse and purchase products with our intuitive interface designed for seamless shopping.',
  },
  {
    icon: <FaTruck className="text-5xl text-teal-400" />,
    title: 'Fast & Reliable Delivery',
    description:
      'Quick processing and delivery of your orders with real-time tracking updates.',
  },
  {
    icon: <FaShieldAlt className="text-5xl text-teal-400" />,
    title: 'Secure Transactions',
    description:
      'Your purchases are protected with enterprise-grade security and encryption.',
  },
  {
    icon: <FaUsers className="text-5xl text-teal-400" />,
    title: 'Customer Focused',
    description:
      'Dedicated to providing the best customer experience with personalized service.',
  },
  {
    icon: <FaHeadset className="text-5xl text-teal-400" />,
    title: '24/7 Support',
    description:
      'Our customer support team is available round the clock to assist you.',
  },
  {
    icon: <FaStar className="text-5xl text-teal-400" />,
    title: 'Quality Products',
    description:
      'Curated selection of high-quality products from trusted suppliers and brands.',
  },
];

export default function ProductFeaturesCarousel() {
  const [currentSlideIndex, setCurrentSlideIndex] = useState(0);

  const nextSlide = () => {
    setCurrentSlideIndex((prevIndex) =>
      prevIndex === featureSlides.length - 1 ? 0 : prevIndex + 1
    );
  };

  const prevSlide = () => {
    setCurrentSlideIndex((prevIndex) =>
      prevIndex === 0 ? featureSlides.length - 1 : prevIndex - 1
    );
  };

  const currentFeature = featureSlides[currentSlideIndex];

  return (
    <section
      className="py-20 text-white"
      style={{
        backgroundImage: 'url(http://googleusercontent.com/file_content/1)',
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundColor: '#1E293B',
      }}
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <h2 className="text-4xl font-bold mb-10">WHY CHOOSE US</h2>
      </div>

      {/* Carousel */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 flex flex-col items-center justify-center">
        <div className="relative w-full max-w-2xl flex items-center justify-center mb-12 min-h-[200px]">
          {/* Left button */}
          <button
            onClick={prevSlide}
            className="absolute left-4 top-1/2 -translate-y-1/2 z-10 p-2 rounded-full bg-gray-800 text-white hover:bg-gray-700 transition"
          >
            <FaChevronLeft size={24} />
          </button>

          {/* Current feature */}
          <div className="text-center">
            <div className="flex justify-center mb-6">{currentFeature.icon}</div>
            <h3 className="text-2xl font-bold mb-4">{currentFeature.title}</h3>
            <p className="text-lg text-gray-300 max-w-md">
              {currentFeature.description}
            </p>
          </div>

          {/* Right button */}
          <button
            onClick={nextSlide}
            className="absolute right-4 top-1/2 -translate-y-1/2 z-10 p-2 rounded-full bg-gray-800 text-white hover:bg-gray-700 transition"
          >
            <FaChevronRight size={24} />
          </button>
        </div>
      </div>

      {/* Grid of features */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 mt-12 grid grid-cols-1 md:grid-cols-3 gap-8">
        {featureSlides.map((feature, index) => (
          <div
            key={index}
            onClick={() => setCurrentSlideIndex(index)}
            className={`flex flex-col items-center text-center p-4 border border-transparent rounded-lg cursor-pointer transition transform hover:scale-105 ${
              index === currentSlideIndex
                ? 'border-teal-400 bg-gray-800 bg-opacity-50'
                : 'hover:border-gray-600'
            }`}
          >
            <div className="mb-4">{feature.icon}</div>
            <h3 className="text-xl font-semibold mb-2">{feature.title}</h3>
            <p className="text-gray-400 text-sm">{feature.description}</p>
          </div>
        ))}
      </div>
    </section>
  );
}
