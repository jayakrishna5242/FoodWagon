
import { Restaurant, MenuItem, Order, AuthResponse, User } from '../types';

const BASE_URL = 'http://localhost:8080/api';

/**
 * HELPER: Normalizes restaurant data
 */
const normalizeRestaurant = (r: any): Restaurant => {
  if (!r) return r;
  const rawCuisines = r.cuisines || r.cuisine || r.cuisineTypes || [];
  return {
    ...r,
    id: Number(r.id),
    name: r.name || 'Unnamed Restaurant',
    imageUrl: r.imageUrl || 'https://images.unsplash.com/photo-1555396273-367ea4eb4db5?q=80&w=1000',
    location: r.location || 'Local Area',
    city: r.city || 'Bangalore',
    rating: r.rating || 0,
    costForTwo: r.costForTwo || '₹500',
    deliveryTime: r.deliveryTime || '30-40 mins',
    cuisines: typeof rawCuisines === 'string' 
      ? rawCuisines.split(',').map((c: string) => c.trim()) 
      : (Array.isArray(rawCuisines) ? rawCuisines : [])
  };
};

/**
 * HELPER: Get current user from storage
 */
const getStoredUser = (): User | null => {
  const saved = localStorage.getItem('foodwagon_user');
  return saved ? JSON.parse(saved) : null;
};

/**
 * HELPER: Parse backend error message
 */
const getErrorMessage = async (response: Response, fallback: string): Promise<string> => {
  try {
    const errorData = await response.json();
    return errorData.message || fallback;
  } catch (e) {
    return fallback;
  }
};

/**
 * AUTHENTICATION
 */
export const loginUser = async (identifier: string, password: string): Promise<AuthResponse> => {
  const response = await fetch(`${BASE_URL}/auth/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ identifier, password }),
  });
  
  if (!response.ok) {
    const msg = await getErrorMessage(response, 'Login failed');
    throw new Error(msg);
  }
  
  const data = await response.json();
  return {
    token: data.token || `session_${Date.now()}`, 
    user: data.user
  };
};

export const registerUser = async (fullName: string, identifier: string, password: string): Promise<AuthResponse> => {
  const isEmail = identifier.includes('@');
  const payload = {
    name: fullName,
    email: isEmail ? identifier : `${identifier}@placeholder.com`,
    password: password,
    phone: !isEmail ? identifier : "0000000000"
  };

  const response = await fetch(`${BASE_URL}/auth/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });

  if (!response.ok) {
    const msg = await getErrorMessage(response, 'Registration failed');
    throw new Error(msg);
  }

  const data = await response.json();
  return {
    token: data.token || `session_${Date.now()}`,
    user: data.user
  };
};

export const loginPartner = async (email: string, password: string): Promise<AuthResponse> => {
  const response = await fetch(`${BASE_URL}/partner/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });

  if (!response.ok) {
    const msg = await getErrorMessage(response, 'Partner login failed');
    throw new Error(msg);
  }
  
  const data = await response.json();
  return {
    token: data.token || `partner_session_${Date.now()}`,
    user: data.user
  };
};

export const registerPartner = async (data: any): Promise<AuthResponse> => {
  const response = await fetch(`${BASE_URL}/partner/register`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    const msg = await getErrorMessage(response, 'Partner registration failed');
    throw new Error(msg);
  }
  
  const result = await response.json();
  return {
    token: result.token || `partner_session_${Date.now()}`,
    user: result.user
  };
};

/**
 * CATALOG & SEARCH
 */
export const fetchRestaurants = async (city?: string): Promise<Restaurant[]> => {
  const url = city ? `${BASE_URL}/restaurants?city=${encodeURIComponent(city)}` : `${BASE_URL}/restaurants`;
  const response = await fetch(url);
  if (!response.ok) return [];
  const data = await response.json();
  return Array.isArray(data) ? data.map(normalizeRestaurant) : [];
};

export const fetchMenu = async (restaurantId: number): Promise<MenuItem[]> => {
  const response = await fetch(`${BASE_URL}/restaurants/${restaurantId}/menu`);
  if (!response.ok) return [];
  return response.json();
};

export const searchGlobal = async (query: string): Promise<{ restaurants: Restaurant[], items: MenuItem[] }> => {
  const response = await fetch(`${BASE_URL}/restaurants/search?q=${encodeURIComponent(query)}`);
  if (!response.ok) return { restaurants: [], items: [] };
  const data = await response.json();
  return {
    restaurants: data.restaurants.map(normalizeRestaurant),
    items: data.items
  };
};

/**
 * MENU MANAGEMENT (PARTNER)
 */
export const addMenuItem = async (item: Partial<MenuItem>): Promise<MenuItem> => {
  const response = await fetch(`${BASE_URL}/menu-items`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(item),
  });
  if (!response.ok) throw new Error("Failed to add menu item");
  return response.json();
};

export const toggleMenuItemStock = async (itemId: number): Promise<MenuItem> => {
  const response = await fetch(`${BASE_URL}/menu-items/${itemId}/toggle-stock`, {
    method: 'PATCH'
  });
  if (!response.ok) throw new Error("Failed to toggle stock");
  return response.json();
};

/**
 * ORDERS
 */
export const placeOrder = async (orderData: any): Promise<Order> => {
  const user = getStoredUser();
  const payload = {
    userId: user?.id,
    restaurantId: orderData.restaurantId,
    items: orderData.items.map((item: any) => ({
      id: item.id,
      name: item.name,
      quantity: item.quantity,
      price: item.price
    })),
    totalAmount: orderData.totalAmount,
    deliveryAddress: orderData.deliveryAddress,
    paymentMethod: orderData.paymentMethod || "COD"
  };

  const response = await fetch(`${BASE_URL}/orders`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload),
  });

  if (!response.ok) throw new Error("Order placement failed");
  return response.json();
};

export const fetchOrders = async (): Promise<Order[]> => {
  const user = getStoredUser();
  if (!user) return [];
  
  const response = await fetch(`${BASE_URL}/orders/user/${user.id}`);
  if (!response.ok) return [];
  return response.json();
};

export const fetchPartnerOrders = async (): Promise<Order[]> => {
  const user = getStoredUser();
  if (!user) return [];

  const restaurant = await fetchPartnerRestaurant(user.id);
  if (!restaurant) return [];

  const response = await fetch(`${BASE_URL}/orders/restaurant/${restaurant.id}`);
  if (!response.ok) return [];
  return response.json();
};

export const updateOrderStatus = async (orderId: number, status: Order['status']): Promise<Order> => {
  const response = await fetch(`${BASE_URL}/orders/${orderId}/status`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ status }),
  });
  
  if (!response.ok) throw new Error("Failed to update status");
  return response.json();
};

/**
 * PARTNER HELPERS
 */
export const fetchPartnerRestaurant = async (partnerId: number): Promise<Restaurant | null> => {
  const response = await fetch(`${BASE_URL}/partner/${partnerId}/restaurant`);
  if (!response.ok) return null;
  const data = await response.json();
  return normalizeRestaurant(data);
};

export const updateRestaurantDetails = async (restaurantId: number, details: Partial<Restaurant>): Promise<Restaurant> => {
  const response = await fetch(`${BASE_URL}/restaurants/${restaurantId}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(details),
  });
  if (!response.ok) throw new Error("Update failed");
  const data = await response.json();
  return normalizeRestaurant(data);
};
