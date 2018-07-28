class ChangeDoneOptionsToDos < ActiveRecord::Migration[5.1]
  def self.up
    change_column :todos, :done, :boolean, null: false, default: false
  end

  def self.down
    change_column :todos, :done, :boolean 
  end
end
