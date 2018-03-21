require "#{Rails.root}/app/controllers/application_controller.rb"

module Api
  module V1
    class TodosController < ApplicationController

      before_action :set_todo, only: [:show, :update, :destroy]

      # GET /api/v1/todo
      def index
        render json: Todo.all
      end

      # POST /api/v1/todo
      def create
        @todo = Todo.new(todo_params)
        if @todo.save
          render json: @todo
        else
          render json: @todo.errors, status: :unprocessable_entity
        end
      end

      # GET /api/v1/todo/:id
      def show
        render json: @todo
      end

      # PATCH /api/v1/todo/:id
      def update
        if @todo.update(todo_params)
          render json: @todo
        else
          render json: @todo.errors, status: :unprocessable_entity
        end

      end

      # DELETE /api/v1/todo/:id
      def destroy
        @todo.destroy
         head :no_content
      end

      private

        def set_todo
          @todo = Todo.find(params[:id])
        end

        def todo_params
          params.require(:todo).permit(:title, :comment, :done)
        end
    end
  end
end
