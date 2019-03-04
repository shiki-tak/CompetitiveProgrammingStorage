namespace :query do
    task :create => :environment do
      p Word.create(text: 'test2', image_url: 'test-image_url2')
    end
    task :show => :environment do
      Word.where(text: 'test').each do |word|
        p word
      end
    end
    task :delete => :environment do
      Word.where(text: 'test').delete()
    end
  end