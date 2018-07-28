Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  namespace :api, format: 'json' do
    namespace :v1 do
      resources :todos
    end
  end

      mount_devise_token_auth_for 'User', at: 'api/v1/auth', controllers: {
          registrations: 'api/v1/auth/registrations'
      }
end
