module Api
    module V1
        class Api::V1::WordController < ApplicationController
            def index
                @words = Word.all
                render json: @words
            end
        end
    end
end