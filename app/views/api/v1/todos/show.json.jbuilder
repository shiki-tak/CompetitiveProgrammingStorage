json.set! :todo do
  json.extract! @todo, :id, :title, :comment, :done, :created_at, :updated_at
end
