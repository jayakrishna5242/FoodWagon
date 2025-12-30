
import React from 'react';
import { Utensils, Award, Users, Heart, ShieldCheck, Zap } from 'lucide-react';

const AboutUs: React.FC = () => {
  return (
    <div className="min-h-screen bg-white pb-20">
      {/* Hero Section */}
      <div className="bg-[#171a29] text-white py-24 text-center px-4">
        <div className="container mx-auto max-w-4xl">
          <div className="inline-block bg-primary p-4 rounded-3xl mb-8">
            <Utensils className="w-12 h-12 text-white" />
          </div>
          <h1 className="text-5xl md:text-6xl font-black mb-6 leading-tight">We are FoodWagon.</h1>
          <p className="text-xl text-gray-400 font-medium max-w-2xl mx-auto leading-relaxed">
            Our mission is to elevate the quality of life for the urban consumer with unparalleled convenience. 
            Convenience is what makes us tick.
          </p>
        </div>
      </div>

      {/* Story Section */}
      <div className="container mx-auto px-4 max-w-6xl py-20">
        <div className="flex flex-col md:flex-row items-center gap-16">
          <div className="md:w-1/2">
            <img 
              src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?q=80&w=2070&auto=format&fit=crop" 
              alt="Restaurant Interior" 
              className="rounded-3xl shadow-2xl"
            />
          </div>
          <div className="md:w-1/2">
            <h2 className="text-3xl font-extrabold text-dark mb-6">Our Journey</h2>
            <p className="text-gray-600 leading-relaxed mb-6">
              Started in 2024 with a vision to revolutionize food delivery, FoodWagon has grown into a community of food lovers and restaurant partners. 
              What began as a small fleet of delivery partners has now expanded into a tech-first logistics platform.
            </p>
            <p className="text-gray-600 leading-relaxed">
              We believe that the best food in the city shouldn't be hard to find. We bridge the gap between hungry consumers 
              and their favorite local eateries, ensuring every meal is delivered with speed and care.
            </p>
          </div>
        </div>
      </div>

      {/* Core Values */}
      <div className="bg-gray-50 py-24">
        <div className="container mx-auto px-4 max-w-6xl">
          <h2 className="text-3xl font-black text-center text-dark mb-20 uppercase tracking-widest">Our Core Values</h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
            <div className="bg-white p-10 rounded-3xl shadow-sm border border-gray-100 group hover:shadow-xl transition-all">
              <div className="w-16 h-16 bg-orange-50 text-primary rounded-2xl flex items-center justify-center mb-8 group-hover:rotate-12 transition-transform">
                <Heart className="w-8 h-8" />
              </div>
              <h3 className="text-xl font-bold mb-4 text-dark">Customer First</h3>
              <p className="text-gray-500 font-medium">We obsess over the consumer experience. Every feature we build starts with how it benefits our users.</p>
            </div>
            
            <div className="bg-white p-10 rounded-3xl shadow-sm border border-gray-100 group hover:shadow-xl transition-all">
              <div className="w-16 h-16 bg-blue-50 text-blue-600 rounded-2xl flex items-center justify-center mb-8 group-hover:rotate-12 transition-transform">
                <Zap className="w-8 h-8" />
              </div>
              <h3 className="text-xl font-bold mb-4 text-dark">Extreme Ownership</h3>
              <p className="text-gray-500 font-medium">We take full responsibility for our outcomes. Excellence is non-negotiable in our logistics and tech.</p>
            </div>

            <div className="bg-white p-10 rounded-3xl shadow-sm border border-gray-100 group hover:shadow-xl transition-all">
              <div className="w-16 h-16 bg-green-50 text-green-600 rounded-2xl flex items-center justify-center mb-8 group-hover:rotate-12 transition-transform">
                <ShieldCheck className="w-8 h-8" />
              </div>
              <h3 className="text-xl font-bold mb-4 text-dark">Hyperlocal Focus</h3>
              <p className="text-gray-500 font-medium">We celebrate the local culture. We support small businesses and local restaurants to thrive in the digital age.</p>
            </div>
          </div>
        </div>
      </div>

      {/* Stats Section */}
      <div className="py-24 text-center">
        <div className="container mx-auto px-4 max-w-6xl">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-12">
            <div>
              <div className="text-5xl font-black text-primary mb-2">500+</div>
              <div className="text-gray-400 font-bold uppercase tracking-widest text-xs">Restaurants</div>
            </div>
            <div>
              <div className="text-5xl font-black text-primary mb-2">10k+</div>
              <div className="text-gray-400 font-bold uppercase tracking-widest text-xs">Happy Users</div>
            </div>
            <div>
              <div className="text-5xl font-black text-primary mb-2">15m</div>
              <div className="text-gray-400 font-bold uppercase tracking-widest text-xs">Avg. Delivery</div>
            </div>
            <div>
              <div className="text-5xl font-black text-primary mb-2">24/7</div>
              <div className="text-gray-400 font-bold uppercase tracking-widest text-xs">Support</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AboutUs;