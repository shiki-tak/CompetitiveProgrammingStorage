class Word
  include Mongoid::Document
  field :text, type: String
  field :image_url, type: String
end
