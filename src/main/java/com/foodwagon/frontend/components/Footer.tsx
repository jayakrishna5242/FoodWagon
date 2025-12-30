
import React from 'react';
import { Utensils } from 'lucide-react';
import { Link } from 'react-router-dom';

const Footer: React.FC = () => {
  return (
    <footer className="bg-black text-white py-12 mt-12">
      <div className="container mx-auto px-4 max-w-7xl">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8 mb-8">
          <div>
            <div className="flex items-center gap-2 mb-4">
              <Utensils className="text-white w-6 h-6" />
              <span className="text-2xl font-bold">FoodWagon</span>
            </div>
            <p className="text-gray-400 text-sm">© 2024 FoodWagon Technologies Pvt. Ltd</p>
          </div>
          
          <div>
            <h3 className="font-bold text-lg mb-4">Company</h3>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li><Link to="/about" className="hover:text-white cursor-pointer transition-colors">About</Link></li>
              <li className="hover:text-white cursor-pointer transition-colors">Careers</li>
              <li className="hover:text-white cursor-pointer transition-colors">Team</li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-4">Contact us</h3>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li className="hover:text-white cursor-pointer transition-colors">Help & Support</li>
              <li className="hover:text-white cursor-pointer transition-colors">Partner with us</li>
              <li className="hover:text-white cursor-pointer transition-colors">Ride with us</li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-4">Legal</h3>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li className="hover:text-white cursor-pointer transition-colors">Terms & Conditions</li>
              <li className="hover:text-white cursor-pointer transition-colors">Cookie Policy</li>
              <li className="hover:text-white cursor-pointer transition-colors">Privacy Policy</li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;